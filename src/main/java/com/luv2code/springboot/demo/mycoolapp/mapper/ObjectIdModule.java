package com.luv2code.springboot.demo.mycoolapp.mapper;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.bson.types.ObjectId;


public class ObjectIdModule extends SimpleModule {

    public ObjectIdModule() {

        super("ObjectIdModule");
    }

    @Override
    public void setupModule(final SetupContext context) {

        this.addSerializer(ObjectId.class, new ToStringSerializer());
        super.setupModule(context);
    }
}
