package com.myisu_1.isu.dto;

import com.myisu_1.isu.models.Barcode.DocUnf;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DocUnfTable {
    private String unfLoad;
    private List<DocUnf> DocUnfTales;
}
