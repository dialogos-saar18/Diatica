# Diatica
This is the source-code repository for the Diatica DialogOS plugin. Diatica was developed during the wintersemester 2018/19 at Saarland University during the "Dialogsysteme" softwareproject. It enables the use of a variety of voice commands to access a habitica account. 

## Goal of the project
We aimed to provide a tool for working with habitica effectivly while having some spare time. Diatica should provide access to the most important features of habitica. Like all due task, ones which are managable in a specified time or marked with a tag, how much health or experience one has or if one wants to rest in the in.

## User stories
1. While one is away some spare time of 30 minutes arises. As to not let it pass by unused one askes Diatica which tasks can be done within 30 minutes. In response to that all due tasks will be listed.
2. You have catched a cold and can therefore not perform any task in habitica (except for going early to bed). For this Diatica can be asked to send the avatar in the inn.
3. You are curious how much experience you have gathered so far. This way you can guess wheather you ar at the beginning of a new level or maybe even close to the next. For this instance Diatica has the ability to state the current experience points.
4. Yesterday many tasks have been left undone. Many of them did cost some health but was it enough to risk losing ones life? Well, just for this purpose one can aks Diatica. Maybe this leads that one will buy a health potion.
5. You are in the mood for being productive but you don't want to be distracted by other, maybe easier, tasks. For this you can use Diatica that will state all the task which have been marked with a tag of your choice. 

## Getting Started

### Prerequisites
For this plugin to work you need:
- A habitica account on http://habitica.com/
  - Once you decide on a language you have to use it consequently. Else does the recognice not understand.
- The apiuser and the apikey of your account. You can find these in the settings of your account under API.
- If you use one of the provided dialogs look in the descriptions of every habitica node and put the specified variables in the stated fields.

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

## Future work
Some features which we would wanted to apply but hadn't the time to or think would be great advantacements:
- Buy potions (and maybe other stuff)
- Being able to complete Tasks based on Tags or no Tags

## Acknowledgments
* Professor Alexander Koller
* the awesome Seminar group, who were always very helpful even when they were struggling themselves
