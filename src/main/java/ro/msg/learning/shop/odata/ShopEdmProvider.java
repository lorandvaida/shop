package ro.msg.learning.shop.odata;

import org.apache.olingo.odata2.api.edm.EdmMultiplicity;
import org.apache.olingo.odata2.api.edm.EdmSimpleTypeKind;
import org.apache.olingo.odata2.api.edm.FullQualifiedName;
import org.apache.olingo.odata2.api.edm.provider.*;

import java.util.ArrayList;
import java.util.List;

public class ShopEdmProvider extends EdmProvider {

    private static final String ENTITY_SET_NAME_PRODUCTS = "Products";
    private static final String ENTITY_SET_NAME_ORDERS = "Orders";
    private static final String ENTITY_NAME_PRODUCT = "Product";
    private static final String ENTITY_NAME_ORDER = "Order";
    private static final String NAMESPACE = " ro.msg.learning.shop.odata";
    private static final FullQualifiedName ENTITY_TYPE_ORDER = new FullQualifiedName(NAMESPACE, ENTITY_NAME_ORDER);
    private static final FullQualifiedName ENTITY_TYPE_PRODUCT = new FullQualifiedName(NAMESPACE, ENTITY_NAME_PRODUCT);
    private static final FullQualifiedName COMPLEX_TYPE_ADDRESS = new FullQualifiedName(NAMESPACE, "Address");
    private static final FullQualifiedName COMPLEX_TYPE_PRODUCT = new FullQualifiedName(NAMESPACE, "Product");
    private static final FullQualifiedName ASSOCIATION_ORDER_PRODUCT =
            new FullQualifiedName(NAMESPACE, "Order_Products_Product_Orders");
    private static final String ROLE_ORDER_PRODUCTS = "Order_Products";
    private static final String ROLE_PRODUCT_ORDER = "Product_Order";
    private static final String ENTITY_CONTAINER = "ODataEntityContainer";
    private static final String ASSOCIATION_SET = "Orders_Products";

    @Override
    public List<Schema> getSchemas() {

        List<Schema> schemas = new ArrayList<>();

        Schema schema = new Schema();
        schema.setNamespace(NAMESPACE);

        List<EntityType> entityTypes = new ArrayList<>();
        entityTypes.add(getEntityType(ENTITY_TYPE_ORDER));
        entityTypes.add(getEntityType(ENTITY_TYPE_PRODUCT));
        schema.setEntityTypes(entityTypes);

        List<ComplexType> complexTypes = new ArrayList<>();
        complexTypes.add(getComplexType(COMPLEX_TYPE_ADDRESS));
        complexTypes.add(getComplexType(COMPLEX_TYPE_PRODUCT));
        schema.setComplexTypes(complexTypes);

        List<Association> associations = new ArrayList<>();
        associations.add(getAssociation(ASSOCIATION_ORDER_PRODUCT));
        schema.setAssociations(associations);

        List<EntityContainer> entityContainers = new ArrayList<>();
        EntityContainer entityContainer = new EntityContainer();
        entityContainer.setName(ENTITY_CONTAINER).setDefaultEntityContainer(true);

        List<EntitySet> entitySets = new ArrayList<>();
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_ORDERS));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_PRODUCTS));
        entityContainer.setEntitySets(entitySets);

        List<AssociationSet> associationSets = new ArrayList<>();
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_ORDER_PRODUCT, ENTITY_SET_NAME_PRODUCTS, ROLE_PRODUCT_ORDER));
        entityContainer.setAssociationSets(associationSets);

        entityContainers.add(entityContainer);
        schema.setEntityContainers(entityContainers);
        schemas.add(schema);

        return schemas;
    }

    @Override
    public EntityType getEntityType(FullQualifiedName edmFQName) {

        if (NAMESPACE.equals(edmFQName.getNamespace())) {

            if (ENTITY_TYPE_ORDER.getName().equals(edmFQName.getName())) {

                List<Property> properties = new ArrayList<>();
                properties.add(new SimpleProperty().setName("orderId").setType(EdmSimpleTypeKind.Int64)
                        .setFacets(new Facets().setNullable(true)));
                properties.add(new SimpleProperty().setName("orderTimestamp").setType(EdmSimpleTypeKind.DateTime)
                        .setFacets(new Facets().setNullable(true)));
                properties.add(new ComplexProperty().setName("deliveryAddress").setType(COMPLEX_TYPE_ADDRESS));

                List<NavigationProperty> navigationProperties = new ArrayList<>();
                navigationProperties.add(new NavigationProperty().setName("orderDetailList")
                        .setRelationship(ASSOCIATION_ORDER_PRODUCT).setFromRole(ROLE_ORDER_PRODUCTS)
                        .setToRole(ROLE_PRODUCT_ORDER));

                List<PropertyRef> keyProperties = new ArrayList<>();
                keyProperties.add(new PropertyRef().setName("orderId"));
                Key key = new Key().setKeys(keyProperties);

                return new EntityType().setName(ENTITY_TYPE_ORDER.getName())
                        .setProperties(properties)
                        .setKey(key)
                        .setNavigationProperties(navigationProperties);

            } else if (ENTITY_TYPE_PRODUCT.getName().equals(edmFQName.getName())) {

                List<Property> properties = new ArrayList<>();
                //properties.add(new SimpleProperty().setName("productId").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));
                properties.add(new ComplexProperty().setName("product").setType(COMPLEX_TYPE_PRODUCT));
                properties.add(new SimpleProperty().setName("quantity").setType(EdmSimpleTypeKind.Int32)
                        .setFacets(new Facets().setNullable(false)));

                List<NavigationProperty> navigationProperties = new ArrayList<>();
                navigationProperties.add(new NavigationProperty().setName("order")
                        .setRelationship(ASSOCIATION_ORDER_PRODUCT)
                        .setFromRole(ROLE_PRODUCT_ORDER).setToRole(ROLE_ORDER_PRODUCTS));

                List<PropertyRef> keyProperties = new ArrayList<>();
                keyProperties.add(new PropertyRef().setName("productId"));
                Key key = new Key().setKeys(keyProperties);

                return new EntityType().setName(ENTITY_TYPE_PRODUCT.getName())
                        .setProperties(properties)
                        .setKey(key)
                        .setNavigationProperties(navigationProperties);
            }
        }

        return null;
    }

    @Override
    public ComplexType getComplexType(FullQualifiedName edmFQName) {

        if (NAMESPACE.equals(edmFQName.getNamespace()) && COMPLEX_TYPE_ADDRESS.getName().equals(edmFQName.getName())) {

            List<Property> properties = new ArrayList<>();
            properties.add(new SimpleProperty().setName("addressCountry").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("addressCity").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("addressCounty").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("addressStreet").setType(EdmSimpleTypeKind.String));

            return new ComplexType().setName(COMPLEX_TYPE_ADDRESS.getName()).setProperties(properties);
        } else if (NAMESPACE.equals(edmFQName.getNamespace()) && COMPLEX_TYPE_PRODUCT.getName().equals(edmFQName.getName())) {

            List<Property> properties = new ArrayList<>();
            properties.add(new SimpleProperty().setName("id").setType(EdmSimpleTypeKind.Int32));
            properties.add(new SimpleProperty().setName("name").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("description").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("price").setType(EdmSimpleTypeKind.Double));
            properties.add(new SimpleProperty().setName("weight").setType(EdmSimpleTypeKind.Double));
            properties.add(new SimpleProperty().setName("categoryName").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("supplierName").setType(EdmSimpleTypeKind.String));

            return new ComplexType().setName(COMPLEX_TYPE_PRODUCT.getName()).setProperties(properties);
        }

        return null;
    }


    @Override
    public Association getAssociation(FullQualifiedName edmFQName) {

        if (NAMESPACE.equals(edmFQName.getNamespace())
                && ASSOCIATION_ORDER_PRODUCT.getName().equals(edmFQName.getName())) {

            return new Association().setName(ASSOCIATION_ORDER_PRODUCT.getName())
                    .setEnd1(new AssociationEnd().setType(ENTITY_TYPE_ORDER)
                            .setRole(ROLE_ORDER_PRODUCTS).setMultiplicity(EdmMultiplicity.ONE))
                    .setEnd2(new AssociationEnd().setType(ENTITY_TYPE_PRODUCT)
                            .setRole(ROLE_PRODUCT_ORDER).setMultiplicity(EdmMultiplicity.MANY));
        }

        return null;
    }

    @Override
    public EntitySet getEntitySet(String entityContainer, String name) {

        if (ENTITY_CONTAINER.equals(entityContainer)) {
            if (ENTITY_SET_NAME_ORDERS.equals(name)) {

                return new EntitySet().setName(name).setEntityType(ENTITY_TYPE_ORDER);
            } else if (ENTITY_SET_NAME_PRODUCTS.equals(name)) {

                return new EntitySet().setName(name).setEntityType(ENTITY_TYPE_PRODUCT);
            }
        }
        return null;
    }


    @Override
    public AssociationSet getAssociationSet(String entityContainer, FullQualifiedName association,
                                            String sourceEntitySetName, String sourceEntitySetRole) {

        if (ENTITY_CONTAINER.equals(entityContainer) && ASSOCIATION_ORDER_PRODUCT.equals(association)) {

            return new AssociationSet().setName(ASSOCIATION_SET)
                    .setAssociation(ASSOCIATION_ORDER_PRODUCT)
                    .setEnd1(new AssociationSetEnd().setRole(ROLE_ORDER_PRODUCTS).setEntitySet(ENTITY_SET_NAME_ORDERS))
                    .setEnd2(new AssociationSetEnd().setRole(ROLE_PRODUCT_ORDER).setEntitySet(ENTITY_SET_NAME_PRODUCTS));
        }

        return null;
    }

    @Override
    public EntityContainerInfo getEntityContainerInfo(String name) {

        if (name == null || ENTITY_CONTAINER.equals(name)) {

            return new EntityContainerInfo().setName(ENTITY_CONTAINER).setDefaultEntityContainer(true);
        }

        return null;
    }

}
