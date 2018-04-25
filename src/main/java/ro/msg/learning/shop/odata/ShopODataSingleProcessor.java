package ro.msg.learning.shop.odata;

import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderReadProperties;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.services.CreateOrderService;
import ro.msg.learning.shop.utils.OrderDtoMapper;

import java.io.InputStream;
import java.util.Map;

@Component
public class ShopODataSingleProcessor extends ODataSingleProcessor {

    private final CreateOrderService createOrderService;

    @Autowired
    public ShopODataSingleProcessor(CreateOrderService createOrderService) {
        
        this.createOrderService = createOrderService;
    }

    @Override
    public ODataResponse createEntity(PostUriInfo uriInfo, InputStream content,
                                      String requestContentType, String contentType) throws ODataException {

        EntityProviderReadProperties properties = EntityProviderReadProperties.init().mergeSemantic(false).build();
        ODataEntry entry = EntityProvider.readEntry(requestContentType,
                uriInfo.getStartEntitySet(), content, properties);
        Map<String, Object> data = entry.getProperties();

        OrderDto createOrderDto = OrderDtoMapper.mapOrderDto(data);
        Order order = createOrderService.createOrder(createOrderDto);

        data.put("orderId", order.getId());

        return EntityProvider.writeEntry("application/xml", uriInfo.getStartEntitySet(), entry.getProperties(),
                EntityProviderWriteProperties.serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
    }


}
