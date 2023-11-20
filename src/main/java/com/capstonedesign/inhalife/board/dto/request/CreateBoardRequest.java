package com.capstonedesign.inhalife.board.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateBoardRequest {

    @NotBlank
    private String name;
}
