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
$ git clone ...
```

Open the directory

```
$ cd search
```

On the root of the project, download and unzip the data:

```
$ curl -O https://s3-sa-east-1.amazonaws.com/luizalabs-tech-challenges/movies.zip

$ unzip moviez.zip
```

Build the project

```
$ mvn clean install
```

## Running the app

After extract and build the project access the target directory:

```
$ cd target
```

```
java -jar search.jar “walt disney”
```


## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
