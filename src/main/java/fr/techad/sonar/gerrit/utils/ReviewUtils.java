package fr.techad.sonar.gerrit.utils;

import org.sonar.api.batch.rule.Severity;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.techad.sonar.gerrit.review.ReviewFileComment;

public final class ReviewUtils {
    private static final Logger LOG = Loggers.get(ReviewUtils.class);
    private static final String UNKNOWN = "UNKNOWN";

    public int thresholdToValue(String threshold) {
        int thresholdValue = ReviewFileComment.UNKNOWN_VALUE;
        
        try {
        	thresholdValue = Severity.valueOf(threshold).ordinal();
        }
        catch (Exception e) {
        	LOG.warn("[GERRIT PLUGIN] Cannot convert threshold String {} to int. Using UNKNOWN.", threshold);
        	thresholdValue = ReviewFileComment.UNKNOWN_VALUE;
        }

        LOG.debug("[GERRIT PLUGIN] {} is converted to {}", threshold, thresholdValue);

        return thresholdValue;
    }

    public String valueToThreshold(int value) {
        String threshold = UNKNOWN;
        
        try {
        	threshold = Severity.values()[value].toString();
        }
        catch (Exception e){
        	LOG.warn("[GERRIT PLUGIN] Cannot convert threshold int {} to String. Using UNKNOWN.", value);
        }

        LOG.debug("[GERRIT PLUGIN] {} is converted to {}", value, threshold);

        return threshold;
    }
}
