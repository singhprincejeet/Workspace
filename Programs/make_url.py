str = "Paramètres communs des parseurs";
array = list(str);
new =[];
for a in array:
    if a == ' ':
        new.append('-')
    elif a.isupper():
        new.append(a.lower())
    else:
        new.append(a)
s = ''.join(new)
s = 'user-guide/' + s
print(s)