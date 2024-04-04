//package HTTPClient;
//
//
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.core.Base64Variant;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.PrettyPrinter;
//import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.databind.Module;
//import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
//import com.fasterxml.jackson.databind.cfg.MutableConfigOverride;
//import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
//import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
//import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
//import com.fasterxml.jackson.databind.jsontype.NamedType;
//import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
//import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
//import com.fasterxml.jackson.databind.node.JsonNodeFactory;
//import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
//import com.fasterxml.jackson.databind.ser.FilterProvider;
//import com.fasterxml.jackson.databind.ser.SerializerFactory;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import java.text.DateFormat;
//import java.util.Locale;
//import java.util.Map;
//import java.util.TimeZone;
//
//    public class FreezableObjectMapper extends ObjectMapper {
//        private boolean frozen = false;
//
//        public FreezableObjectMapper freeze() {
//            frozen = true;
//
//            return this;
//        }
//
//        private void checkFrozen() {
//            if (frozen)
//                throw new UnsupportedOperationException(
//                        "This ObjectMapper has been frozen and should not be edited. You probably meant to use DefaultObjectMapper.create() rather than DefaultObjectMapper.JSON");
//        }
//
//        @Override
//        public ObjectMapper registerModule(Module module) {
//            checkFrozen();
//
//            return super.registerModule(module);
//        }
//
//        @Override
//        public ObjectMapper registerModules(Module... modules) {
//            checkFrozen();
//
//            return super.registerModules(modules);
//        }
//
//        @Override
//        public ObjectMapper registerModules(Iterable<? extends Module> modules) {
//            checkFrozen();
//
//            return super.registerModules(modules);
//        }
//
//        @Override
//        public ObjectMapper setSerializerFactory(SerializerFactory f) {
//            checkFrozen();
//
//            return super.setSerializerFactory(f);
//        }
//
//        @Override
//        public ObjectMapper setSerializerProvider(DefaultSerializerProvider p) {
//            checkFrozen();
//
//            return super.setSerializerProvider(p);
//        }
//
//        @Override
//        public ObjectMapper setMixIns(Map<Class<?>, Class<?>> sourceMixins) {
//            checkFrozen();
//
//            return super.setMixIns(sourceMixins);
//        }
//
//        @Override
//        public ObjectMapper addMixIn(Class<?> target, Class<?> mixinSource) {
//            checkFrozen();
//
//            return super.addMixIn(target, mixinSource);
//        }
//
//        @Override
//        public ObjectMapper setMixInResolver(ClassIntrospector.MixInResolver resolver) {
//            checkFrozen();
//
//            return super.setMixInResolver(resolver);
//        }
//
//        @Override
//        public void setMixInAnnotations(Map<Class<?>, Class<?>> sourceMixins) {
//            checkFrozen();
//
//            super.setMixInAnnotations(sourceMixins);
//        }
//
//        @Override
//        public void setVisibilityChecker(VisibilityChecker<?> vc) {
//            checkFrozen();
//
//            super.setVisibilityChecker(vc);
//        }
//
//        @Override
//        public ObjectMapper setVisibility(VisibilityChecker<?> vc) {
//            checkFrozen();
//
//            return super.setVisibility(vc);
//        }
//
//        @Override
//        public ObjectMapper setVisibility(PropertyAccessor forMethod, JsonAutoDetect.Visibility visibility) {
//            checkFrozen();
//
//            return super.setVisibility(forMethod, visibility);
//        }
//
//        @Override
//        public ObjectMapper setSubtypeResolver(SubtypeResolver str) {
//            checkFrozen();
//
//            return super.setSubtypeResolver(str);
//        }
//
//        @Override
//        public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector ai) {
//            checkFrozen();
//
//            return super.setAnnotationIntrospector(ai);
//        }
//
//        @Override
//        public ObjectMapper setAnnotationIntrospectors(
//                AnnotationIntrospector serializerAI, AnnotationIntrospector deserializerAI) {
//            checkFrozen();
//
//            return super.setAnnotationIntrospectors(serializerAI, deserializerAI);
//        }
//
//        @Override
//        public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy s) {
//            checkFrozen();
//
//            return super.setPropertyNamingStrategy(s);
//        }
//
//        @Override
//        public ObjectMapper setSerializationInclusion(JsonInclude.Include incl) {
//            checkFrozen();
//
//            return super.setSerializationInclusion(incl);
//        }
//
//        @Override
//        public ObjectMapper setPropertyInclusion(JsonInclude.Value incl) {
//            checkFrozen();
//
//            return super.setPropertyInclusion(incl);
//        }
//
//        @Override
//        public ObjectMapper setDefaultPrettyPrinter(PrettyPrinter pp) {
//            checkFrozen();
//
//            return super.setDefaultPrettyPrinter(pp);
//        }
//
//        @Override
//        public ObjectMapper enableDefaultTyping() {
//            checkFrozen();
//
//            return super.enableDefaultTyping();
//        }
//
//        @Override
//        public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping) {
//            checkFrozen();
//
//            return super.enableDefaultTyping(defaultTyping);
//        }
//
//        @Override
//        public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping, JsonTypeInfo.As as) {
//            checkFrozen();
//
//            return super.enableDefaultTyping(defaultTyping, as);
//        }
//
//        @Override
//        public ObjectMapper enableDefaultTypingAsProperty(DefaultTyping defaultTyping, String s) {
//            checkFrozen();
//
//            return super.enableDefaultTypingAsProperty(defaultTyping, s);
//        }
//
//        @Override
//        public ObjectMapper disableDefaultTyping() {
//            checkFrozen();
//
//            return super.disableDefaultTyping();
//        }
//
//        @Override
//        public ObjectMapper setDefaultTyping(TypeResolverBuilder<?> typer) {
//            checkFrozen();
//
//            return super.setDefaultTyping(typer);
//        }
//
//        @Override
//        public void registerSubtypes(Class<?>... classes) {
//            checkFrozen();
//
//            super.registerSubtypes(classes);
//        }
//
//        @Override
//        public void registerSubtypes(NamedType... types) {
//            checkFrozen();
//
//            super.registerSubtypes(types);
//        }
//
//        @Override
//        public MutableConfigOverride configOverride(Class<?> type) {
//            checkFrozen();
//
//            return super.configOverride(type);
//        }
//
//        @Override
//        public ObjectMapper setTypeFactory(TypeFactory f) {
//            checkFrozen();
//
//            return super.setTypeFactory(f);
//        }
//
//        @Override
//        public ObjectMapper setNodeFactory(JsonNodeFactory f) {
//            checkFrozen();
//
//            return super.setNodeFactory(f);
//        }
//
//        @Override
//        public ObjectMapper addHandler(DeserializationProblemHandler h) {
//            checkFrozen();
//
//            return super.addHandler(h);
//        }
//
//        @Override
//        public ObjectMapper clearProblemHandlers() {
//            checkFrozen();
//
//            return super.clearProblemHandlers();
//        }
//
//        @Override
//        public ObjectMapper setConfig(DeserializationConfig config) {
//            checkFrozen();
//
//            return super.setConfig(config);
//        }
//
//        @Override
//        public void setFilters(FilterProvider filterProvider) {
//            checkFrozen();
//
//            super.setFilters(filterProvider);
//        }
//
//        @Override
//        public ObjectMapper setFilterProvider(FilterProvider filterProvider) {
//            checkFrozen();
//
//            return super.setFilterProvider(filterProvider);
//        }
//
//        @Override
//        public ObjectMapper setBase64Variant(Base64Variant v) {
//            checkFrozen();
//
//            return super.setBase64Variant(v);
//        }
//
//        @Override
//        public ObjectMapper setConfig(SerializationConfig config) {
//            checkFrozen();
//
//            return super.setConfig(config);
//        }
//
//        @Override
//        public ObjectMapper setDateFormat(DateFormat dateFormat) {
//            checkFrozen();
//
//            return super.setDateFormat(dateFormat);
//        }
//
//        @Override
//        public Object setHandlerInstantiator(HandlerInstantiator hi) {
//            checkFrozen();
//
//            return super.setHandlerInstantiator(hi);
//        }
//
//        @Override
//        public ObjectMapper setInjectableValues(InjectableValues injectableValues) {
//            checkFrozen();
//
//            return super.setInjectableValues(injectableValues);
//        }
//
//        @Override
//        public ObjectMapper setLocale(Locale l) {
//            checkFrozen();
//
//            return super.setLocale(l);
//        }
//
//        @Override
//        public ObjectMapper setTimeZone(TimeZone tz) {
//            checkFrozen();
//
//            return super.setTimeZone(tz);
//        }
//
//        @Override
//        public ObjectMapper configure(MapperFeature f, boolean state) {
//            checkFrozen();
//
//            return super.configure(f, state);
//        }
//
//        @Override
//        public ObjectMapper enable(MapperFeature... f) {
//            checkFrozen();
//
//            return super.enable(f);
//        }
//
//        @Override
//        public ObjectMapper disable(MapperFeature... f) {
//            checkFrozen();
//
//            return super.disable(f);
//        }
//
//        @Override
//        public ObjectMapper configure(SerializationFeature f, boolean state) {
//            checkFrozen();
//
//            return super.configure(f, state);
//        }
//
//        @Override
//        public ObjectMapper enable(SerializationFeature f) {
//            checkFrozen();
//
//            return super.enable(f);
//        }
//
//        @Override
//        public ObjectMapper enable(SerializationFeature first, SerializationFeature... f) {
//            checkFrozen();
//
//            return super.enable(first, f);
//        }
//
//        @Override
//        public ObjectMapper disable(SerializationFeature f) {
//            checkFrozen();
//
//            return super.disable(f);
//        }
//
//        @Override
//        public ObjectMapper disable(SerializationFeature first, SerializationFeature... f) {
//            checkFrozen();
//
//            return super.disable(first, f);
//        }
//
//        @Override
//        public ObjectMapper configure(DeserializationFeature f, boolean state) {
//            checkFrozen();
//
//            return super.configure(f, state);
//        }
//
//        @Override
//        public ObjectMapper enable(DeserializationFeature feature) {
//            checkFrozen();
//
//            return super.enable(feature);
//        }
//
//        @Override
//        public ObjectMapper enable(DeserializationFeature first, DeserializationFeature... f) {
//            checkFrozen();
//
//            return super.enable(first, f);
//        }
//
//        @Override
//        public ObjectMapper disable(DeserializationFeature feature) {
//            checkFrozen();
//
//            return super.disable(feature);
//        }
//
//        @Override
//        public ObjectMapper disable(DeserializationFeature first, DeserializationFeature... f) {
//            checkFrozen();
//
//            return super.disable(first, f);
//        }
//
//        @Override
//        public ObjectMapper configure(JsonParser.Feature f, boolean state) {
//            checkFrozen();
//
//            return super.configure(f, state);
//        }
//
//        @Override
//        public ObjectMapper enable(JsonParser.Feature... features) {
//            checkFrozen();
//
//            return super.enable(features);
//        }
//
//        @Override
//        public ObjectMapper disable(JsonParser.Feature... features) {
//            checkFrozen();
//
//            return super.disable(features);
//        }
//
//        @Override
//        public boolean isEnabled(JsonGenerator.Feature f) {
//            checkFrozen();
//
//            return super.isEnabled(f);
//        }
//
//        @Override
//        public ObjectMapper configure(JsonGenerator.Feature f, boolean state) {
//            checkFrozen();
//
//            return super.configure(f, state);
//        }
//
//        @Override
//        public ObjectMapper enable(JsonGenerator.Feature... features) {
//            checkFrozen();
//
//            return super.enable(features);
//        }
//
//        @Override
//        public ObjectMapper disable(JsonGenerator.Feature... features) {
//            checkFrozen();
//
//            return super.disable(features);
//        }
//    }
//
