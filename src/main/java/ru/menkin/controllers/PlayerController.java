package ru.menkin.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
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

/**
 * @author Menkin
 * @since 10.11.2015
 */

@RequestMapping(value = "/view")
@Controller
public class PlayerController {
    private final SpringStorage storage;

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

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    @ResponseBody
    public String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();

        if (!file.isEmpty()) {
            try (InputStream stream = file.getInputStream()) {
                ReadXls readXls = new ReadXls(stream);
                List<Player> list = readXls.convert();
                for (Player player : list) {
                    storage.add(player);
                }
                modelAndView.addObject("players", list);
                modelAndView.setViewName("View");
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return modelAndView;
    }
}