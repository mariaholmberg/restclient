package maho.jaxrs;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.WebTarget;

public class RestClient {
        private List<ClientRequestFilter> requestFilters = new ArrayList<ClientRequestFilter>();
        private Client client;

//        @Inject
        public RestClient() {
//            this(ClientBuilder.newClient().register(setupCustomJacksonProvider()));
            this(ClientBuilder.newClient());
        }


        RestClient(Client client) {
            this.client = client;
        }

//        private static JacksonJaxbJsonProvider setupCustomJacksonProvider() {
//            AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
//            AnnotationIntrospector secondary = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
//            AnnotationIntrospector pair = AnnotationIntrospector.pair(primary, secondary);
//            ObjectMapper oMapper = new ObjectMapper();
//            oMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY).setAnnotationIntrospector(pair);
//            oMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            oMapper.registerModule(new SimpleModule("money")
//                                           .addSerializer(Money.class, new MoneySerializer())
//                                           .addDeserializer(Money.class, new MoneyDeserializer()));
//            JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
//            provider.setMapper(oMapper);
//            return provider;
//        }

        public RestClient withRequestFilter(ClientRequestFilter requestFilter){
            requestFilters.add(requestFilter);
            return this;
        }

//        public RestClient withHttpLogging() {
//            client.register(new LoggingFilter());
//            return this;
//        }

        public WebTarget getTarget(URI uri) {
            WebTarget target = client.target(uri);
            for (ClientRequestFilter requestFilter : requestFilters) {
                target.register(requestFilter);
            }
            return target;
        }

    }
