# Search Engine

A simple program to index and search terms on files

### Prerequisites


```
java 1.8
maven
curl
git
```

### Installing

Clone the project

```
$ git clone https://github.com/chicoalmeida/search.git
```

Open the directory

```
$ cd search
```

On the root of the project, download and unzip the data:

```
$ curl -O https://s3-sa-east-1.amazonaws.com/luizalabs-tech-challenges/movies.zip

$ unzip movies.zip
```

Build the project

```
$ mvn clean install
```

## Running the app

```
java -jar target/search.jar “walt disney”
```
