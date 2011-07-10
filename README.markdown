## About
morphia-support is a library for morphia (http://code.google.com/p/morphia/). Especially with Spring/Jackson.

## Examples

### Spring

To create `com.google.code.morphia.Datastore`,  add below in your bean definition file

    <bean class="am.ik.support.morphia.spring.MorphiaDataStoreFactoryBean">
        <property name="dbName" value="your MongoDB's db name" />
        <property name="host" value="your MongoDB's host name" />
    </bean>

This factory automatically closes a `com.mongodb.Mongo` object when the application server closes.

### Jackson

    import org.bson.types.ObjectId;
    import org.codehaus.jackson.map.annotate.JsonSerialize;
    
    import am.ik.support.morphia.jackson.ObjectIdSerializer;
    
    import com.google.code.morphia.annotations.Entity;
    import com.google.code.morphia.annotations.Id;
    
    @Entity
    public class Person {
        @Id
        private ObjectId id;
        private String name;
        
        // setter/getter
        
        @JsonSerialize(using = ObjectIdSerializer.class)
        public ObjectId getId() {
            return id;
        }
    }

### Spring MVC

    @Controller
    public class XxxController {
    
        @InitBinder
        public void initBinder(WebDataBinder binder) throws Exception {
            binder.registerCustomEditor(ObjectId.class, "id", new ObjectIdEditor());
        }
        
        // request mappings
    }

## Use with Maven

At first, add repository in your pom.

    <repositories>
        ...

        <repository>
            <id>making-dropbox-releases</id>
            <name>making's Maven Release Repository</name>
            <url>http://dl.dropbox.com/u/342817/maven/releases</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>

And add dependency.

    <dependencies>
        ...

        <dependency>
            <groupId>am.ik.support.morphia</groupId>
            <artifactId>morphia-support</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>


## License

Licensed under the Apache License, Version 2.0.