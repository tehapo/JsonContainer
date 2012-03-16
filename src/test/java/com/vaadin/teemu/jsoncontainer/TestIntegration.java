package com.vaadin.teemu.jsoncontainer;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.vaadin.teemu.jsoncontainer.JsonContainer;

import com.vaadin.data.Item;

public class TestIntegration {

    private JsonContainer container;

    @Before
    public void setup() {
        container = JsonContainer.Factory
                .newInstance("[ {   \"name\" : \"Teemu\",   \"age\" : 31   }, {   \"name\" : \"Teemu\",   \"age\" : 31   }, {   \"name\" : \"Test\",   \"age\" : 35   }, {   \"name\" : \"Johnny\",   \"age\" : 25, \"language\": \"English\"  } ]");
    }

    @Test
    public void numberOfItems() {
        Assert.assertEquals(4, container.size());
    }

    @Test
    public void numberOfPropertyIds() {
        Assert.assertEquals(3, container.getContainerPropertyIds().size());
    }

    @Test
    public void propertyIds() {
        Assert.assertTrue(container.getContainerPropertyIds().contains("name"));
        Assert.assertTrue(container.getContainerPropertyIds().contains("age"));
        Assert.assertTrue(container.getContainerPropertyIds().contains(
                "language"));
    }

    @Test
    public void itemValues() {
        Item firstItem = container.getItem(container.getIdByIndex(0));
        Assert.assertEquals("Teemu", firstItem.getItemProperty("name")
                .getValue());
        Assert.assertEquals("31", firstItem.getItemProperty("age").getValue());
        Assert.assertEquals(null, firstItem.getItemProperty("language")
                .getValue());

        Item secondItem = container.getItem(container.getIdByIndex(1));
        Assert.assertEquals("Teemu", secondItem.getItemProperty("name")
                .getValue());
        Assert.assertEquals("31", secondItem.getItemProperty("age").getValue());
        Assert.assertEquals(null, secondItem.getItemProperty("language")
                .getValue());

        Item thirdItem = container.getItem(container.getIdByIndex(2));
        Assert.assertEquals("Test", thirdItem.getItemProperty("name")
                .getValue());
        Assert.assertEquals("35", thirdItem.getItemProperty("age").getValue());
        Assert.assertEquals(null, thirdItem.getItemProperty("language")
                .getValue());

        Item fourthItem = container.getItem(container.getIdByIndex(3));
        Assert.assertEquals("Johnny", fourthItem.getItemProperty("name")
                .getValue());
        Assert.assertEquals("25", fourthItem.getItemProperty("age").getValue());
        Assert.assertEquals("English", fourthItem.getItemProperty("language")
                .getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void malformedJson() {
        JsonContainer.Factory.newInstance("::");
    }

    @Test
    public void singleJsonObject() {
        JsonContainer container = JsonContainer.Factory
                .newInstance("{   \"name\" : \"Teemu\",   \"age\" : 31   }");
        Assert.assertEquals(1, container.size());
        Assert.assertEquals(true,
                container.getContainerPropertyIds().contains("name"));
        Assert.assertEquals(true,
                container.getContainerPropertyIds().contains("age"));
        Assert.assertEquals(
                "Teemu",
                container.getItem(container.getIdByIndex(0))
                        .getItemProperty("name").getValue());
        Assert.assertEquals("31", container.getItem(container.getIdByIndex(0))
                .getItemProperty("age").getValue());
    }
}
