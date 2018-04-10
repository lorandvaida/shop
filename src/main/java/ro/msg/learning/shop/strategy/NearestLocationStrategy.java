package ro.msg.learning.shop.strategy;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.mapper.Distance;
import ro.msg.learning.shop.mapper.Element;
import ro.msg.learning.shop.mapper.LocationMapper;
import ro.msg.learning.shop.mapper.Row;
import ro.msg.learning.shop.services.LocationService;
import ro.msg.learning.shop.services.StockService;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@PropertySource("classpath:config.properties")
public class NearestLocationStrategy implements LocationStrategy {

    private final LocationService locationService;

    @Value("${proxy.host}")
    private String host;
    @Value("${proxy.port}")
    private int port;
    @Value("${api.key}")
    private String API_KEY;

    @Autowired
    public NearestLocationStrategy(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public Location getLocation(CreateOrderDto createOrderDto) {

        List<Location> availableLocationsList = locationService.getAvailableLocationsForOrder(createOrderDto);

        SimpleClientHttpRequestFactory clientHttpReq = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
        clientHttpReq.setProxy(proxy);

        String origins = parseAvailableLocationsToString(availableLocationsList);
        String destionations = createOrderDto.getDeliveryAddress().getAddressStreet() + " " + createOrderDto.getDeliveryAddress().getAddressCity();

        String url="https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origins+"&destinations="+destionations+"&key="+ API_KEY;

        RestTemplate restTemplate = new RestTemplate(clientHttpReq);
        LocationMapper mappedLocations = restTemplate.getForObject(url , LocationMapper.class);

        return getNearestLocationFromMapper(mappedLocations);
    }

    private String parseAvailableLocationsToString(List<Location> availableLocationsList) {

        StringBuilder sb = new StringBuilder();

        for(Location location : availableLocationsList) {

            sb.append(location.getAddress().getAddressStreet());
            sb.append(" ");
            sb.append(location.getAddress().getAddressCity());
            sb.append("|");
        }

        String result = sb.toString();

        //deletes the last '|' character
        return result.substring(0, result.length()-1);
    }

    private Location getNearestLocationFromMapper(LocationMapper mappedLocations) {

        String[] originAddresses = mappedLocations.getOrigin_addresses();
        Row[] rows = mappedLocations.getRows();
        List<Element> elementList = new ArrayList<>();

        for(Row row : rows) {

            Element[] elementsArray = row.getElements();
            elementList.add(elementsArray[0]);
        }

        Element[] elements = elementList.toArray(new Element[elementList.size()]);

        int minDistance = distanceToInt(elements[0].getDistance());
        int index = 0;
        int minIndex = 0;

        for(Element element :elements) {

            Distance distance = element.getDistance();
            int distanceKm = distanceToInt(distance);

            if(distanceKm < minDistance) {

                minDistance = distanceKm;
                minIndex = index;
            }
            index++;
        }

        String nearestLocationStringAddress = originAddresses[minIndex];
        Address address = getAddressFromParsedString(nearestLocationStringAddress);

        return locationService.getLocationByAddress(address);
    }

    private int distanceToInt(Distance distance) {

        return (int) distance.getValue()/100;
    }


    private Address getAddressFromParsedString(String location) {

        Address address = new Address();
        String[] split = location.split(", ");

        address.setAddressStreet(split[0]);
        address.setAddressCity(split[1]);
        address.setAddressCountry(split[2]);

        return address;
    }

}
