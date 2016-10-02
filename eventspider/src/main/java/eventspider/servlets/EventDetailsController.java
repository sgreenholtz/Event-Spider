//package eventspider.servlets;
//
//import eventspider.beans.EventBean;
//import eventspider.database.EventHandler;
//
//import java.io.*;
//import java.util.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//
///**
// * Controls the event details view and selects the event to view
// * @author Sebastian Greenholtz
// */
//
//public class EventDetailsController extends HttpServlet {
//
//    private static Properties properties;
//
//    /**
//     * Gets the value from the search display view to select an event
//     * @param request
//     * @param response
//     */
//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//        throws IOException, ServletException {
//        EventBean eventBean = null;
//        if (request.getSession().getAttribute("returnPage").equals("search-result-list")) {
//            Map<Integer, EventBean> eventsMap = (HashMap) request.getSession().getAttribute("eventsMap");
//            eventBean = eventsMap.get(new Integer(request.getParameter("id")));
//        } else {
//            eventBean = getEventBean(new Integer(request.getParameter("id")));
//        }
//
//        request.setAttribute("event", eventBean);
//        String url = "/event-details";
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//        dispatcher.forward(request, response);
//    }
//
//    /**
//     * Gets the event bean associated with this ID
//     * @param eventID Integer ID for the event to create a bean of
//     * @return Event Bean of that event
//     */
//    private EventBean getEventBean(Integer eventID) {
//        EventHandler eventHandler = new EventHandler();
//        return eventHandler.getEventByID(eventID);
//    }
//}