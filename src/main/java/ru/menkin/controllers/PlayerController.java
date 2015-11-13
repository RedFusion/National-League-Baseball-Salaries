package ru.menkin.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;
import ru.menkin.models.*;
import ru.menkin.store.*;
import ru.menkin.utils.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.*;

/**
 * @author Menkin
 * @since 10.11.2015
 */

@RequestMapping(value = "/view")
@Controller
public class PlayerController {
    private final SpringStorage storage;
    final AtomicInteger ids = new AtomicInteger();

    @Autowired
    public PlayerController(final SpringStorage storage) {
        this.storage = storage;
    }

    //http://localhost:8080/Players/api/view/list
    //view and sort table with players
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest req) throws ServletException, IOException {

        String key = req.getParameter("key");
        String typeSort = req.getParameter("sort");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("players", storage.values());
        modelAndView.addObject("sort", typeSort == null ? "abs" : typeSort);
        modelAndView.addObject("key", key == null ? "team" : key);

        ArrayList<Player> list = new ArrayList<Player>();
        list.addAll(storage.values());

        if (key != null && typeSort != null) {
            SortCollection sortClass = new SortCollection(list, key, typeSort);
            sortClass.sortCollection();
            modelAndView.addObject("players", list);
        }
        //jsp name
        modelAndView.setViewName("View");

        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createView(ModelAndView model){
        model.setViewName("CreatePlayer");
        return model;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public String create(@RequestParam("team") String team, @RequestParam("name") String name,
                         @RequestParam("salary") String salary,@RequestParam("position") String position){
        storage.add(new Player(ids.incrementAndGet(), team, name, salary, position));
        return "redirect:list";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    @ResponseBody
    public String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
//        ModelAndView modelAndView = new ModelAndView();

        if (!file.isEmpty()) {
            try (InputStream stream = file.getInputStream()) {
                ReadXls readXls = new ReadXls(stream);
                List<Player> list = readXls.convert();
                list.forEach(storage::add);
//                modelAndView.addObject("players", list);
//                modelAndView.setViewName("View");
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return "redirect:list";
    }
}