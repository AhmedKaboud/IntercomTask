package com.intercom.deserializers;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonDeserializationContext;
import com.intercom.constants.Constant;
import com.intercom.models.Customer;
import com.intercom.models.Location;

import java.lang.reflect.Type;

/**
 * A custom deserializer to deserialize Customer objects.
 */
public class CustomerDeserializer implements JsonDeserializer<Customer> {

    @Override
    public Customer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Location location = new Location(
                jsonObject.get(Constant.LATITUDE_KEY).getAsDouble(),
                jsonObject.get(Constant.LONGITUDE_KEY).getAsDouble());

        return new Customer(jsonObject.get(Constant.USER_ID_KEY).getAsInt(),
                jsonObject.get(Constant.NAME_KEY).getAsString(),
                location);
    }
}
