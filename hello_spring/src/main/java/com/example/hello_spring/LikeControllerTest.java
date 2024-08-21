package com.example.hello_spring;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.example.hello_spring.DTO.Music;
import com.example.hello_spring.controller.LikeController;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class LikeControllerTest {

    @InjectMocks
    private LikeController likeController;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSetReportNewComplaint() throws IOException {
        // Arrange
        String name = "1111NewSongName2121231231111111";
        String singer = "111NewSingerName231231111111";
        String album = "11NewAlbumName12312311111111";

        Music newMusic = new Music(album, name, singer);
        List<Music> musicList = new ArrayList<>();

        when(objectMapper.readValue(any(InputStream.class), eq(Music[].class)))
                .thenReturn(new Music[0]);

        ObjectWriter writer = mock(ObjectWriter.class);
        when(objectMapper.writer(any(DefaultPrettyPrinter.class))).thenReturn(writer);

        ResponseEntity<?> response = likeController.setReport(name, singer, album);
        assertEquals(ResponseEntity.ok("Жалоба успешно отправлена!"), response);
    }

    @Test
    public void testSetReportExistingComplaint() throws IOException {
        // Arrange
        String name = "ExistingSongName11";
        String singer = "ExistingSingerName1";
        String album = "ExistingAlbumName1";

        Music existingMusic = new Music(album, name, singer);
        List<Music> musicList = new ArrayList<>(Arrays.asList(existingMusic));

        when(objectMapper.readValue(any(InputStream.class), eq(Music[].class)))
                .thenReturn(new Music[]{existingMusic});

        // Act
        ResponseEntity<?> response = likeController.setReport(name, singer, album);

        // Assert
        assertEquals(ResponseEntity.ok("ОШИБКА отправки жалобы! Жалоба УЖЕ существует"), response);

    }
}
