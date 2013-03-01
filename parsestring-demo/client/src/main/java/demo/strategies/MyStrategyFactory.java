package demo.strategies;

import org.talend.esb.servicelocator.cxf.internal.LocatorSelectionStrategy;
import org.talend.esb.servicelocator.cxf.internal.RandomSelectionStrategyFactory;

public class MyStrategyFactory extends RandomSelectionStrategyFactory {
    private int reloadAdressesCount = 10;

    public void setReloadAdressesCount(int reloadAdressesCount) {
        this.reloadAdressesCount = reloadAdressesCount;
    }

    @Override
    public LocatorSelectionStrategy getInstance() {
        MyStrategy strategy = new MyStrategy();
        strategy.setReloadAdressesCount(reloadAdressesCount);
        return strategy;
    }
}
