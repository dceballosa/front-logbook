package com.rgfp.psd.logbook.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NoteTest {

    // your tests here
    @Test
    public void deberiaTraer15PrimerasLetrasPalabra15() {

        // Arrange
        Note note = new Note();
        note.setContent("123456789012345");
        // Act
        Integer result = note.getSummary().length();
        // Assert
        assertEquals(new Integer(15), result);
    }

    @Test
    public void deberiaTraer15PrimerasLetrasPalabraMayor15() {

        // Arrange
        Note note = new Note();
        note.setContent("123456789012345678");
        // Act
        Integer result = note.getSummary().length();
        // Assert
        assertEquals(new Integer(15), result);
    }


    @Test
    public void deberiaTraer15PrimerasLetrasPalabraMenor15() {

        // Arrange
        Note note = new Note();
        note.setContent("1234567890");
        // Act
        Integer result = note.getSummary().length();
        // Assert
        assertEquals(new Integer(10), result);
    }


    @Test
    public void deberiaTraer15PrimerasLetrasDelContentParaUnCaso() {

        // Arrange
        Note note = new Note();
        note.setContent("Compras semanales");
        // Act
        String result = note.getSummary();
        // Assert
        assertEquals("Compras semanal", result);
    }

    @Test
    public void deberiaTraer15PrimerasLetrasDelContentParaMasUnCaso() {

        // Arrange
        Note note1 = new Note();
        note1.setContent("Compras semanales");

        Note note2 = new Note();
        note2.setContent("Para pagar el lavado del auto");

        // Act
        String result1 = note1.getSummary();

        String result2 = note2.getSummary();

        // Assert
        assertEquals("Compras semanal", result1);
        assertEquals("Para pagar el l", result2);
    }

    @Test
    public void cuandoClonoUnaTareaLaTareaSeCopia() {

        // Arrange
        Note note = new Note();
        // Act
        Note noteCopia = note.clone();
        // Assert
        assertEquals(note.getTitle(), noteCopia.getTitle());
        assertEquals(note.getContent(), noteCopia.getContent());
    }
}