package com.practice.fileservice.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.fileservice.FileTestDataBuilder;
import com.practice.fileservice.controller.FileController;
import com.practice.fileservice.model.File;
import com.practice.fileservice.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FileController.class)
class FileRestTest {

	private final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private FileService fileService;

	@Test
	void findById() throws Exception {
		File file = FileTestDataBuilder.fileBuilder();
		when(fileService.findById(file.getId())).thenReturn(file);
		String fileJson = objectMapper.writeValueAsString(file);

		mockMvc.perform(get("/file/{id}", file.getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(file.getId()))
				.andExpect(MockMvcResultMatchers.content().string(fileJson))
				.andDo(MockMvcResultHandlers.print());

		verify(fileService).findById(file.getId());
	}

	@Test
	void save() throws Exception {
		File file = FileTestDataBuilder.fileBuilder();
		when(fileService.save(file)).thenReturn(file);
		String fileJson = objectMapper.writeValueAsString(file);

		mockMvc.perform(post("/file").contentType(MediaType.APPLICATION_JSON).content(fileJson))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(file.getId()))
				.andExpect(MockMvcResultMatchers.content().string(fileJson))
				.andDo(MockMvcResultHandlers.print());

		verify(fileService).save(file);
	}

	@Test
	void delete() throws Exception {
		File file = FileTestDataBuilder.fileBuilder();

		mockMvc.perform(MockMvcRequestBuilders.delete("/file/{id}", file.getId()))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

		verify(fileService).delete(file.getId());
	}

	@Test
	void findByIds() throws Exception {
		File file = FileTestDataBuilder.fileBuilder();
		when(fileService.findByIds(List.of(file.getId()))).thenReturn(List.of(file));
		String fileJson = objectMapper.writeValueAsString(List.of(file));

		mockMvc.perform(post("/file/list").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(List.of(file.getId()))))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().string(fileJson))
				.andDo(MockMvcResultHandlers.print());

		verify(fileService).findByIds(List.of(file.getId()));
	}
}