package com.woowahan.recipe.domain.dto.reviewDto;

import com.woowahan.recipe.domain.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewGetResponse {
    private Long reviewId;
    private String username;
    private String review_comment;
    private LocalDateTime createdDate;
    private LocalDateTime last_modified;

    public static ReviewGetResponse toReviewGetResponse(ReviewEntity review) {
        return ReviewGetResponse.builder()
                .reviewId(review.getReviewId())
                .username(review.getUser().getUserName())
                .review_comment(review.getReview_comment())
                .createdDate(review.getCreatedDate())
                .last_modified(review.getLastModifiedDate())
                .build();
    }

}
