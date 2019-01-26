# Diatica
This is the source-code repository for the Diatica DialogOS plugin. Diatica was developed during the wintersemester 2018/19 at Saarland University during the "Dialogsysteme" softwareproject. It enables the use of a variety of voice commands to access a habitica account. 

## Getting Started

### Prerequisites
For this plugin to work you need:
- A habitica account on http://habitica.com/
- The apiuser and the apikey of your account. You can find these in the settings of your account under API.
### Installing
Look into the `gradle.build` file and update the dependencies (mainly DialogOS) if necessary.
```
./gradlew build
```
Gradle will now build the project and deposit the files in the build directory. In `/build/libs/` you will find the necessary .jar file of the plugin. Simply copy or move this file in your DialogOS directory into the `Plugins` folder. 
On a mac it would properly look like this `/Applications/DialogOS/plugins`.


Open a Dialouge in DialogOS. And go to the variables, where you have to add 5 variables(name,type,value):
  - tag, String, ""
  - apiuser, String, "your userid"
  - apikey, String, "your api-token/key"
  - input, String, "" - You have to set this variable in the Habitca node
  - output, String, "" - You have to set this variable in the Habitca node
  
 Just drag&drop the Habitca node from the sidebar into your Dialogue and set the `eingang` to `input` and  `ausgabe` to `output`.

## Running the Plugin
The plugin sends a httprequest to habitca and processes the response it gets from habitica. There are different keywords for every command, but some commands need extra data therefore you need to assign a value to `input` and to `tag` in the grammar:
- if you want to send a `sleep` or `wake up` request you need to set the values to either `["sleep",""]` or `["wake_up",""]` 
- if you want either hp or exp set the values to `["hp",""]` or `["exp",""]`
- if you want to set the tags `["add_tags",""]`
- if you want the dailys `["all_due_tasks",""]`
- if you want the specific tasks based on a tag set the value of `tag` `["spec_task","1h"]`



## Built With

* [Gradle](https://github.com/gradle/gradle) - Build Framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [DialogOS](https://github.com/dialogos-project/dialogos) - Dialogsystem

## Acknowledgments
* Professor Alexander Koller
* the awesome Seminar group, who were always very helpful even when they were struggling themselves
