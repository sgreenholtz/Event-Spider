# Event-Spider
Ever wonder what's going on in your city right now? Ever want to do *something* this weekend, but have no idea what events
are happening close by? Event Spider is here to help! This friendly arachnid looks at common event hosting sites like
Eventful and MeetUp, and crawls the web for local newspapers, universities, and anywhere else people are posting about
events, and displays all the results, no log in needed!

If you like what you see, you can create an account to save events to your personalized event calendar, and the Event
Spider will spin you a web of suggestions based on what you've saved in the past. You can set email reminders, send events
to your friends, or just keep it all to yourself and brag about how much fun you're having. Event Spider won't judge.

#### Digital Ocean
Note: The server may not be running when you access the website. I'm still working on development. If there is interest
I will keep it running all the time.
[Link](http://138.68.1.193:8090)

#### Progress
Check out my [project wiki](https://github.com/sgreenholtz/Event-Spider/wiki) for detailed updates.

#### Current State
As of the beginning of the Fall 2016 semester, the following components have been built:
- User registration and login
- Indexing and searching the database
- Search for events on Eventful
- Viewing events and adding to user account

#### Goals for Fall 2016
- User dashboard with personal event calendar
- Bring in Google Calendar API
- Share buttons to publish events to social media or email self reminders
- Finish crawling service and deploy to cloud server for faster processing
- Event recommendation engine with event tagging/categories

#### New Technologies to Add
- Spring Boot
- Hibernate ORM
- Log4j
- JDBC real authentication
- 80% code coverage with jUnit tests