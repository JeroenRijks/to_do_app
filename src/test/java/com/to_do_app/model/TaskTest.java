package com.to_do_app.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void testCreateTask(){
        Task testTask = new Task();
        testTask.setImportance(PriorityTypes.LOW);
        assertEquals(testTask.getImportance(), PriorityTypes.LOW);
    }

}