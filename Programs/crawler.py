from bs4 import BeautifulSoup
from tabulate import tabulate
import urllib2

http_links = []
https_links = []
slash_links = []
without_slash_links = []
capture = {}
to_visit = []
visited = []
table = []
header = ["Page URL", "Captured Issue URL"]


def get_links(url, parent):
    if url.endswith('/'):
        url = url[0:len(url)-1]
    response = ''
    try:
        response = urllib2.urlopen(url)
    except urllib2.HTTPError, e:
        print e.msg
    page = BeautifulSoup(response, 'lxml')
    a_tags = page.find_all("a")
    links = []
    for link in a_tags:
        links.append(link.get('href'))
    for link in links:
        if link is not None:
            if link.startswith('http://'):
                http_links.append(link)
                if link.startswith(url):
                    to_visit.append(link)
            elif link.startswith('https://'):
                https_links.append(link)
                add_to_capture(url, link)
            elif link.startswith('/') and len(link) > 1:
                link = parent + link
                slash_links.append(link)
                if link.startswith(link):
                    to_visit.append(link)
            elif not (link.startswith('/') | link.startswith('#') | link.startswith('mail') | link.startswith('java')):
                without_slash_links.append(link)
                add_to_capture(url, link)


def add_to_capture(url, link):
    if url in capture:
        capture[url].append(link)
    else:
        capture[url] = [link]


def crawl_web(url, parent):
    to_visit.append(url)
    while to_visit:
        link = to_visit.pop()
        if link not in visited:
            get_links(link, parent)
            visited.append(link)
    for i in capture:
        capture[i] = uniquify(capture[i])


def uniquify(lst):
    keys = {}
    for e in lst:
        keys[e] = 1
    return keys.keys()


for i in capture:
    for j in capture[i]:
        table.append([i, j])

print tabulate(table, header)
