package io.jenkins.plugins.analysis.core.model;

import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

import java.util.ArrayList;
import java.util.List;

public class Widget {

    private final String symbol;
    private final List<String> lines = new ArrayList<>();
    private final List<ResultAction> failedSymbols;

    public Widget(@MonotonicNonNull List<ResultAction> result) {

        failedSymbols = result.stream().filter(e -> e.getResult().getTotalSize() > 0).toList();

        int failCount = result.stream().map(e -> e.getResult().getTotalSize()).reduce(0, Integer::sum);
        boolean isFailed = failCount > 0;

        this.symbol = isFailed ? "symbol-warning-outline plugin-ionicons-api" : "symbol-status-blue";

        List<String> counts = new ArrayList<>();

        if (isFailed) {
            lines.add(failCount + " warnings");
        } else {
            lines.add("No warnings");
        }

        lines.add(String.join(", ", counts));
    }

    public String getSymbol() {
        return symbol;
    }

    public List<String> getLines() {
        return lines;
    }

    public List<ResultAction> getFailedSymbols() {
        return failedSymbols;
    }
}
