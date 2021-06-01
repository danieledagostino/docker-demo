package org.pincio.games.dto;

import java.util.List;

public class PagePersonDto {

    private List<PersonDto> personDtoList;
    private int totalElements;
    private int totalPages;

    public List<PersonDto> getPersonDtoList() {
        return personDtoList;
    }

    public void setPersonDtoList(List<PersonDto> personDtoList) {
        this.personDtoList = personDtoList;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
