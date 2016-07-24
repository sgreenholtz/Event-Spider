# Event-Spider
Ever wonder what's going on in your city right now? Ever want to do *something* this weekend, but have no idea what events
are happening close by? Event Spider is here to help! This friendly arachnid looks at common event hosting sites like
Eventful and MeetUp, and crawls the web for local newspapers, universities, and anywhere else people are posting about
events, and displays all the results, no log in needed!

If you like what you see, you can create an account to save events to your personalized event calendar, and the Event
Spider will spin you a web of suggestions based on what you've saved in the past. You can set email reminders, send events
to your friends, or just keep it all to yourself and brag about how much fun you're having. Event Spider won't judge.

#### Technologies
- Event Spider is built using plain ol' Java, with a MySQL database and Tomcat server.
- Web crawling is performed using [Apache Nutch] (http://nutch.apache.org/) with [Solr](http://lucene.apache.org/solr/) to parse the results
- Current configuration runs only on a local machine, however the MVP will be launched on [OpenShift](https://www.openshift.com/)

#### Progress
Check out my [project wiki](https://github.com/sgreenholtz/Event-Spider/wiki) for detailed updates.

- 7/24/16: I reconfigured the web modules so a lot of what was working previously is mysteriously broken. Working on
fixing it now.
