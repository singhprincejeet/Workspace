str = "Programme de bourses ontariennes etudes Trillium";
strSplit = str.split();
new = []
for a in strSplit:
    if a == 'and'or a =='And':
        continue
    elif a == 'et':
        continue
    elif a == 'du':
        continue
    elif a == 'de':
        continue
    elif a == 'with':
        continue
    elif a == 'avec':
        continue
    elif a == 'a'or a =='A':
        continue
    elif a == 'la':
        continue
    else:
        new.append(a)
s = '-'.join(new)       
array = list(s);
new =[];
for a in array:
    if a.isupper():
        new.append(a.lower())
    else:
        new.append(a)
s = ''.join(new)
s = 'students/awards/' + s
print(s)