package cn.fengylb.mycommunity.mycommunity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer page, Integer size, Integer questionsCount) {
        if (questionsCount % size == 0){
            totalPage = questionsCount / size;
        }else{
            totalPage = questionsCount / size + 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        this.page = page;
        if (page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        if (page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }
        pages.add(page);
        for(int i = 1;i < 4;i++){
            if (page + i <= totalPage){
                pages.add(page + i);
            }
            if (page - i > 0){
                pages.add(0,page - i);
            }
        }
        if (pages.contains(1)) {
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }
        if (pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }
    }
}
