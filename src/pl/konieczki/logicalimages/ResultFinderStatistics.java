package pl.konieczki.logicalimages;

import lombok.Getter;
import lombok.NonNull;
import pl.konieczki.logicalimages.strategies.FindResultStrategy;

import java.util.HashMap;
import java.util.Map;

public class ResultFinderStatistics {
    @Getter
    private int counter = 0;
    private final Map<FindResultStrategy, Integer> strategiesUsage = new HashMap<>();

    public void init() {
        counter = 0;
        strategiesUsage.clear();
    }

    public void process(@NonNull FindResultStrategy strategy, boolean makeChanges) {
        counter++;
        if (makeChanges) {
            if (strategiesUsage.containsKey(strategy)) {
                final Integer count = strategiesUsage.get(strategy);
                strategiesUsage.replace(strategy, count + 1);
            } else {
                strategiesUsage.put(strategy, 1);
            }
        }
    }
}