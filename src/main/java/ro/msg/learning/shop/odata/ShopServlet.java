package ro.msg.learning.shop.odata;

import org.apache.olingo.odata2.core.servlet.ODataServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
class ShopServlet extends ODataServlet {

    private final ShopCreateOrderServiceFactory shopCreateOrderServiceFactory;

    @Autowired
    public ShopServlet(ShopCreateOrderServiceFactory shopCreateOrderServiceFactory) {
        this.shopCreateOrderServiceFactory = shopCreateOrderServiceFactory;
    }

    @Override
    protected ShopCreateOrderServiceFactory getServiceFactory(HttpServletRequest request) {

        return shopCreateOrderServiceFactory;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

        req.setAttribute(ShopCreateOrderServiceFactory.FACTORY_INSTANCE_LABEL, shopCreateOrderServiceFactory);
        super.service(req, res);
    }
}
