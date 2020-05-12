# TrainBookingSystem
火车订票系统

# workflow注意点
- 注明开发者。每个人在开发的时候，要写上自己的名字，方便后序监测的时候找到相关人员！
- 自己负责自己的代码。不要改变别人的代码，如果一定要改变，一定要询问对方是否正在修改！
- 注释代码内容。注释的部分可以少，可以简单，但是一定要有！

# workflow
- git pull 拉取源仓库的内容
- 添加和修改自己的代码，不要随便修改别人的内容！
- git add 
- git commit 提交自己的代码到本次仓库
- git pull再次拉取内容，然后git会自动合并本地仓库的内容
- git push 提交到远程仓库

前端代码
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href='/webjars/bootstrap/4.4.1/css/bootstrap.min.css' rel='stylesheet'>
    <title>earthquake_manager</title>
</head>

<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="#">地震灾情系统管理端</a>
</nav>
    <div class="container" id="main">
        <br>
        <br>
        <br>
        <form @submit.prevent="postInfo">
            <div class="form-group">
                <label for="code">Code</label>
                <input type="text" class="form-control" id="code" placeholder="19位编码" v-model="code">
            </div>

            <div class="form-group">
                <label for="location">Location</label>
                <input type="text" class="form-control" id="location" placeholder="位置" v-model="location">
            </div>

            <div class="form-group">
                <label for="date">Date</label>
                <input type="date" class="form-control" id="date" placeholder="日期" v-model="date">
            </div>

            <div class="form-group">
                <label for="note">Note</label>
                <input type="text" class="form-control" id="note" placeholder="描述" v-model="note">
            </div>

            <div class="form-group">
                <label for="picture">Picture</label>
                <input type="text" class="form-control" id="picture" placeholder="图片" v-model="picture">
            </div>

            <div class="form-group">
                <label for="reportingUnit">ReportingUnit</label>
                <input type="text" class="form-control" id="reportingUnit" placeholder="上报的单位" v-model="reportingUnit">
            </div>

            <div class="form-group">
                <label for="type">Type</label>
                <select class="form-control" id="type" v-model="type">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
            </div>
            <div class="form-group">
                <label for="status">Status</label>
                <select  class="form-control" id="status" v-model="status">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
            </div>
            <button class="btn btn-primary" type="submit">提交</button>
        </form>
    </div>


<script src="/webjars/jquery/3.0.0/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="/webjars/axios/0.19.0/dist/axios.min.js"></script>
<script src="/webjars/vue/2.6.11/vue.min.js"></script>
<script>
    new Vue({
        el: "#main",
        data() {
            return {
                code: null,
                location: null,
                date: null,
                type: null,
                status: null,
                note: null,
                picture: null,
                reportingUnit: null,
            }
        },
        methods: {
            postInfo: function(event){
                axios
                .post("http://localhost:8080/api/earthquake/newOne", this.getObject())
                .then(savedInfo => {
                    if (String(savedInfo.data) === "true") {
                        alert("上传成功");
                        this.code = null;
                        this.location = null;
                        this.date = null;
                        this.type = null;
                        this.status = null;
                        this.note = null;
                        this.picture = null;
                        this.reportingUnit = null;
                    }else{
                        alert("上传失败");
                    }
                })
            },
            getObject: function () {
                return {
                    "code": this.code,
                    "location": this.location,
                    "date": this.date,
                    "type": this.type,
                    "status": this.status,
                    "note": this.note,
                    "picture": this.picture,
                    "reportingUnit": this.reportingUnit
                };
            }
        }
    });


</script>
</body>
</html>
```

后端代码
```java
package com.example.demo.controller.earthquake;

import com.example.demo.model.earhtquake.EarthquakeEntity;
import com.example.demo.service.earthquake.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/earthquake")
public class EarthquakeRestController {
    @Autowired
    private EarthquakeService earthquakeService;

    /**
     * 获取地震清单列表
     * @return
     */
    @GetMapping("/list")
    public List<EarthquakeEntity> getAll() {

        return earthquakeService.findAll();
    }

    /**
     * 上传一个一条新的信息
     * @param earthquakeEntity
     * @return
     */
    @PostMapping("/newOne")
    public Boolean saveOne(@RequestBody EarthquakeEntity earthquakeEntity) {
        earthquakeService.saveOne(earthquakeEntity);
        return true;
    }

    /**
     * 根据id来查询信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public EarthquakeEntity findOne(@PathVariable int id) {
        return earthquakeService.findById(id);
    }


    /**
     * 更新一条数据，记得要将id设置为参数
     * @param id
     * @param date
     * @param location
     * @param note
     * @param picture
     * @param reportingUnit
     * @param type
     * @param status
     * @return
     */
    @PutMapping("/list")
    public EarthquakeEntity update(@RequestParam int id, @RequestParam String date, @RequestParam String location,
                                   @RequestParam String note, @RequestParam String picture, @RequestParam String reportingUnit,
                                   @RequestParam char type, @RequestParam char status) {
        EarthquakeEntity earthquakeEntity  = new EarthquakeEntity();
        earthquakeEntity.setId(id);
        earthquakeEntity.setDate(date);
        earthquakeEntity.setLocation(location);
        earthquakeEntity.setNote(note);
        earthquakeEntity.setPicture(picture);
        earthquakeEntity.setReportingUnit(reportingUnit);
        earthquakeEntity.setType(type);
        earthquakeEntity.setStatus(status);
        return earthquakeService.saveOne(earthquakeEntity);
    }

    /**
     * 删除一个记录
     * @param id
     */
    @PostMapping("/list/{id}")
    public void delete(@PathVariable int id) {
        earthquakeService.deleteOne(id);
    }

    /**
     * 根据地点查询相关信息
     * @param location
     * @return
     */
    @PostMapping("/list/by")
    public List<EarthquakeEntity> findByLocation(@RequestParam String location) {
        return earthquakeService.findByLocation(location);
    }

    @PostMapping("/list/byNote")
    public List<EarthquakeEntity> findByNoteContains(@RequestParam String note) {
        return earthquakeService.findByNoteContains(note);
    }

    @PostMapping("/list/byLen")
    public List<EarthquakeEntity> findByJPQL(@RequestParam int len) {
        return earthquakeService.findByJPQl(len);
    }

    @PostMapping("/list/update")
    public EarthquakeEntity update(@RequestParam String note, @RequestParam int id) {
        earthquakeService.updateByJPQL(note, id);
        return findOne(id);
    }
}

```