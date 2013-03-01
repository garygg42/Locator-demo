package demo.strategies;

import java.util.Collections;
import java.util.List;

import org.talend.esb.servicelocator.cxf.internal.RandomSelectionStrategy;

public class MyStrategy extends RandomSelectionStrategy {
    @Override
    protected List<String> getRotatedList(List<String> strings) {
        Collections.rotate(strings, -1);
        return strings;
    }
}
