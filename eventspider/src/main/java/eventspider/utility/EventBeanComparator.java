package eventspider.utility;

import eventspider.beans.EventBean;

import java.util.Comparator;

/**
 * Custom comparator for EventBean by date
 * @author Sebastian Greenholtz
 */
public class EventBeanComparator implements Comparator<EventBean> {
    @Override
    public int compare(EventBean t1, EventBean t2) {
        return t1.getStartDate().compareTo(t2.getStartDate());
    }
}
