package ru.menkin.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;
import ru.menkin.models.*;
import ru.menkin.store.*;
import ru.menkin.utils.*;

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
    private final ISpringStorage storage;
    final AtomicInteger ids = new AtomicInteger();

    @Autowired
    public PlayerController(final ISpringStorage storage) {
        this.storage = storage;
    }

    //associate with PlayerStorage and use ISpringStorage
    @Autowired
    public Storages storages;

    //http://localhost:8080/Players/api/view/list
    //view and sort table with players
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@ModelAttribute Sort sort){
        String key = sort.getKey();
        String typeSort = sort.getSort();

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
    public String create(@RequestParam("team") String team, @RequestParam("name") String name,
                         @RequestParam("salary") String salary,@RequestParam("position") String position){
        storages.playerStorage.add(new Player(ids.incrementAndGet(), team, name, salary, position));
        return "redirect:list";
    }

    //default view for /upload URL
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    @ResponseBody
    public String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try (InputStream stream = file.getInputStream()) {
                ReadXls readXls = new ReadXls(stream);
                List<Player> list = readXls.convert();
                list.forEach(storages.playerStorage::add);
            } catch (Exception e) {
                return "Problems with file upload " + e.getMessage();
            }
        }
        return "redirect:list";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editView(@RequestParam("id") String id){
        ModelAndView model = new ModelAndView();
        model.setViewName("EditPlayer");
        model.addObject("player", storage.get(Integer.valueOf(id)));
        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestParam("id") String id, @RequestParam("team") String team,
                       @RequestParam("name") String name, @RequestParam("salary") String salary,
                       @RequestParam("position") String position){
        storages.playerStorage.edit(new Player(Integer.valueOf(id), team, name, salary, position));
        return "redirect:list";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") String id){
        storages.playerStorage.delete(Integer.valueOf(id));
        return "redirect:list";
    }
}