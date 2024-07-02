#bin/sh
x="mvn test | wc"

y=$(eval "$x")
echo "$y"