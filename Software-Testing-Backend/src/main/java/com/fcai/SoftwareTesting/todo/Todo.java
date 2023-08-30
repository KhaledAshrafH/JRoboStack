package com.fcai.SoftwareTesting.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private String id;
    private String title;
    private String description;
    private boolean completed;
}
