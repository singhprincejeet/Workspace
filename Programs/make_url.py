str = "Hours and Locations";
strSplit = str.split();
new = []
for a in strSplit:
    if a == 'and'or a =='And':
        continue
    elif a == 'les':
        continue
    elif a == 'des':
        continue
    elif a == 'le':
        continue
    elif a == 'et':
        continue
    elif a == 'en':
        continue
    elif a == 'du':
        continue
    elif a == 'de':
        continue
    elif a == 'per':
        continue
    elif a == 'like':
        continue
    elif a == 'into':
        continue
    elif a == 'with':
        continue
    elif a == 'for':
        continue
    elif a == 'to':
        continue
    elif a == 'on':
        continue
    elif a == 'of':
        continue
    elif a == 'or':
        continue
    elif a == 'while':
        continue
    elif a == 'off':
        continue
    elif a == 'by':
        continue
    elif a == 'avec':
        continue
    elif a == 'donc':
        continue
    elif a == 'a'or a =='A':
        continue
    elif a == 'an'or a =='An':
        continue
    elif a == 'the'or a =='The':
        continue
    elif a == 'la':
        continue
    elif a == 'then':
        continue
    elif a == 'in':
        continue
    elif a == 'via':
        continue
    elif a == 'as':
        continue
    elif a == 'un':
        continue
    elif a == 'une':
        continue
    elif a == 'ni':
        continue
    elif a == 'dans':
        continue
    elif a == 'car':
        continue
    elif a == 'sur':
        continue
    elif a == 'sous':
        continue
    elif a == 'ou':
        continue
    elif a == 'mais':
        continue
    elif a == 'sauf':
        continue
    elif a == 'but':
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
s = 'tutorials/' + s
print(s)