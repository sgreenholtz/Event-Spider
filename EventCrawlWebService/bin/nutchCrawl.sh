#!/bin/bash
apache-nutch/bin/crawl -i -D solr.server.url=http://localhost:8983/solr/gettingstarted urls/ crawl/ 1