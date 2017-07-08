package central

import javafx.collections.ObservableArray
import rx.Observable

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.io.IOException
import java.io.PrintWriter

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SimpleServlet extends HttpServlet {

    static Logger log = LoggerFactory.getLogger((SimpleServlet.class));

    def h1(content) {
        return "<h1>${content}</h1>"
    }

    def row(td1, td2) {
        return "<tr><td>${td1}</td><td>${td2}</td></tr>"
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        def out = response.getWriter()

        out.println "<html>"
        out.println "<head>"
        out.println "<title>the date</title>"
        out.println "<link rel='stylesheet' type='text/css' href= '${request.getContextPath()}/css/style.css'/>"
        out.println "</head>"
        out.println "<body>"

        def today = new Date()
        out.println h1(today)

        def names = ["Taro", "Jiro", "Saburo"]

        Observable.from(names).subscribe{
            out.println h1("Hello ${it}!")
        }

        def numbers = [3, 4, 5, 6, 7]

        numbers.each {it -> out.println h1("${it} squared is ${it * it}")}

        out.println "</body>"
        out.println "</html>"

        def my_hash = ["cool": "boston", "hot": "pheonix", "city": "new york", "fun": "san diego"]

        out.println "<table>"
        my_hash.each { key, value -> out.println row(key, value)}
        out.println "</table>"

        log.debug "debug - blah blah crap whatever"
        log.info "info - I am curious to know about this"
        log.warn "warn - oh oh, something not good just happened"
        log.error "error! - run for your life!!!!"
    }
}
