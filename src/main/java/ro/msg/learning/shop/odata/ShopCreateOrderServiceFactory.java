package ro.msg.learning.shop.odata;

import org.apache.olingo.odata2.api.ODataCallback;
import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataErrorCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.services.CreateOrderService;

@Component
public class ShopCreateOrderServiceFactory extends ODataServiceFactory {

    private final CreateOrderService createOrderService;

    @Autowired
    public ShopCreateOrderServiceFactory(CreateOrderService createOrderService) {
        this.createOrderService = createOrderService;
    }

    @Override
    public ODataService createService(ODataContext oDataContext) {

        return createODataSingleProcessorService(new ShopEdmProvider(), new ShopODataSingleProcessor(createOrderService));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ODataCallback> T getCallback(final Class<T> callbackInterface) {

        if (callbackInterface.equals(ODataErrorCallback.class)) {

            return (T) (ODataErrorCallback) oDataErrorContext -> null;
        }

        return super.getCallback(callbackInterface);
    }
}