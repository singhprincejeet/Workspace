"""
 This method returns the source code of the page whose url is provided.
 @:param url It is the web address o the web page being crawled.
"""
def get_page(url):
    try:
        import urllib
        return urllib.urlopen(url).read()
    except:
        return ''


"""
 This method finds the next link in the sourcecode and returns the link and the position at which the search ended.
 @:param page It is the source code from which links are being extracted
 @:param url It is the web address o the web page being crawled.
 @:param end_quote It is the position at which the search fpr web address ended
"""
def get_next_target(page):
    start_link = page.find('<a href=')
    if start_link == -1: 
        return None, 0
    start_quote = page.find('"', start_link)
    end_quote = page.find('"', start_quote + 1)
    url = page[start_quote + 1:end_quote]
    return url, end_quote

"""
 This method loops over the whole source code and looks for looks in it and append those links to a list.
 @:param page It is the source code from which links are being extracted
 @:return links It is a list of links from the web page.
"""
def get_all_links(page):
    links = []
    while True:
        url, endpos = get_next_target(page)
        if url:
            links.append(url)
            page = page[endpos:]
        else:
            break
    return links

"""
 This method appends the element from list b to elements in list a, only if that element does not already exist in list a.
 @:param a It is the list in which elements will be appended
 @:param b It is the list from which elements will be appended
"""
def union(a, b):
    for e in b:
        if e not in a:
            a.append(e)

"""
 This method add the keyword to dictionary index as key and appends the url to its value as part of list.
 If keyword already exist in the index then the url is appended to the list associated with it.
 @:param index It is a dictionary in which to every key(keyword) a value(list of websites associated to keywords) is stored.
 @:param keyword It is a keyword associated to which we will find the best web address.
 @:param url It is the web address o the web page being crawled.
"""
def add_to_index(index, keyword, url):
    if keyword in index:
        index[keyword].append(url)
    else:
        index[keyword] = [url]

"""
 This method goes through the content in the page and add every word to the index along with the URL of the page.
 @:param index It is a dictionary in which to every key(keyword) a value(list of websites associated to keywords) is stored.
 @:param url It is the web address o the web page being crawled.
 @:param content It is the source code of the web page
"""
def add_page_to_index(index, url, content):
    if content:
        words = content.split()
        for word in words:
            add_to_index(index, word, url)
    else:
        exit()

"""
 This method returns the list of url(s) associated with a key word in the index dictionary.
 @:param index It is a dictionary in which to every key(keyword) a value(list of websites associated to keywords) is stored.
 @:param keyword It is a keyword associated to which we will find the best web address.
"""
def lookup(index, keyword):
    if keyword in index:
        return index[keyword]
    else:
        return None

"""
 This method goes through the whole page, make an index dictionary in which every keyword is assocuated with respective
 web address. It also keep track of the sites the pages links to.
 @:param seed It is the web address of the web page to be crawled.
"""
def crawl_web(seed): # returns index, graph of inlinks
    tocrawl = [seed]
    crawled = []
    graph = {}  # <url>, [list of pages it links to]
    index = {} 
    while tocrawl: 
        page = tocrawl.pop()
        if page not in crawled:
            content = get_page(page)
            add_page_to_index(index, page, content)
            outlinks = get_all_links(content)
            graph[page] = outlinks
            union(tocrawl, outlinks)
            crawled.append(page)
    return index, graph

"""
 This method creates a dictionary ranks, in which to every keyword(page link) a rank is associated as value.
 @:param graph It keeps track of the sites the pages links to.
 @:return ranks It is a dictionary in which to every key(web address) a value(rank of webb address) is stored
"""
def compute_ranks(graph):
    d = 0.85 # damping factor
    numloops = 10
    
    ranks = {}
    npages = len(graph)
    for page in graph:
        ranks[page] = 1.0 / npages
    
    for i in range(0, numloops):
        newranks = {}
        for page in graph:
            newrank = (1 - d) / npages
            for node in graph:
                if page in graph[node]:
                    newrank = newrank + d * (ranks[node] / len(graph[node]))
            newranks[page] = newrank
        ranks = newranks
    return ranks

"""
 Lucky search Method is to return the website that matches the keyword and is the best match for it.
 To achieve this we first find the pages that match the keyword in index. If nothing is found we return None.
 Otherwise we compare all the pages and return the page with the best rank.
 @:param index It is a dictionary in which to every key(keyword) a value(list of websites associated to keywords) is stored.
 @:param ranks It is a dictionary in which to every key(web address) a value(rank of webb address) is stored
 @:param keyword It is a keyword associated to which we will find the best web address.
 @:return result It is the best web address associated with keyword.
"""
def lucky_search(index, ranks, keyword):
    pages = lookup(index,keyword)
    if not pages:
        return None
    result = pages[0]
    for check in pages:
        if ranks[check] > ranks[result]:
            result = check
    return result

index, graph = crawl_web('any weblink')#name of the website goes here
ranks = compute_ranks(graph)
print lucky_search(index, ranks, '')#put keyword to get the best result
