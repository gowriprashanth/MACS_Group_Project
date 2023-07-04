package com.project.accomatch.Service;

import com.project.accomatch.Model.Posts;

import java.util.List;
import java.util.Map;

public interface ApplicantPostFilterService {

    List<Posts> filterPost(Map<String, String> jsonMap);
}
