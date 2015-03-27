# Introduction #

MinuteProject presents a set of reverse-engineering capabilities based on the Lazuly conference db.

Lazuly conference db in version 0.1 contains 13 tables, 2 views.

The following 'smart' reverse-engineering solutions will be demonstrated for:
  * jpa2
  * openxava
  * vaadin
  * playframework
  * grails

# Details #

Lazuly database holds information related to conference information such as conference\_member, speaker, sponsors, speech\_evalutions ...

![http://lazuly.googlecode.com/files/lazuly-0.1.svg](http://lazuly.googlecode.com/files/lazuly-0.1.svg)

The goal is to have a working business model that illustrate some complexity and show how by enriching with the minimun set of info, there is the possibility to have comprehensive reverse-engineering solutions that spans over different applications artifacts and are not limited to creating Domain Object in an single directory.

The user will learn how to use minuteproject to:
  * package the entities into logical business bundles
  * enrich with a primary key strategy
  * create enums for specific fields
  * add conventions that affects all model entities
  * enrich views to add them a primary key and foreign keys