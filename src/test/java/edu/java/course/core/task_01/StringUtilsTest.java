package edu.java.course.core.task_01;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    @Test
    void should_normalize_text() {
        final String result = StringUtils.normalize("LoLkeKaTel");
        assertThat(result).isEqualTo("lolkekatel");
    }

    @Test
    void should_split_on_lines() {
        final List<String> lines = StringUtils.splitOnLines("Line 1\nLine 2\nLine 3");

        assertThat(lines).isEqualTo(List.of("Line 1", "Line 2", "Line 3"));
    }

    @Test
    void should_count_chars() {
        final Map<Character, Integer> result = StringUtils.countChars("ForK bork");
        assertThat(result).containsExactlyInAnyOrderEntriesOf(
                Map.ofEntries(
                        Map.entry('F', 1),
                        Map.entry('o', 2),
                        Map.entry('r', 2),
                        Map.entry('K', 1),
                        Map.entry(' ', 1),
                        Map.entry('k', 1),
                        Map.entry('b', 1)
                )
        );
    }

    @Test
    void should_replace_all_dots_with_comma() {
        final String result = StringUtils.replaceAllDots("Fork. bork. New stage. ");
        assertThat(result).isEqualTo("Fork, bork, New stage, ");
    }

    @Test
    void should_reverse() {
        final String result = StringUtils.reverse("trololo");
        assertThat(result).isEqualTo("ololort");
    }

    @Test
    void should_merge_to_single_string() {
        final String result = StringUtils.mergeToSingleString(List.of("lol", "kek"));
        assertThat(result).isEqualTo("lolkek");
    }
}