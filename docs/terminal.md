# TIPS & TRICKS :: TERMINAL
[[back to index page]](index.md)

Useful commands for terminal, terminal software configuration, etc.

## Contents
1. [Configuring iTerm text navigation](#configuring-iterm-text-navigation)
2. [Useful bash commands](#useful-shell-commands)
3. [Useful GIT commands](#useful-git-commands)
   1. [Using custom SSH key](#using-custom-ssh-key)
   2. [Customizing Github repo](#customizing-github-repo)
4. [Useful Docker commands](#useful-docker-commands)

## Configuring iTerm text navigation
1. Open Preferences -> Profiles -> Keys
2. Change default mapping to:
```shell
FOR  ACTION           SEND
⌘←   "SEND HEX CODE"  0x01 
⌘→   "SEND HEX CODE"  0x05
⌥←   "SEND ESC SEQ"   b
⌥→   "SEND ESC SEQ"   f
```

## Useful shell commands
```shell
# Getting OS name and version
cat /etc/*-release

# Determining kernel architecture
uname -a

# Printing out N last command lines
history N

# Getting an output of a file copied to clipboard
cat ~/.ssh/id_rsa.pub | pbcopy

# Finding a file by regex pattern
find . -regex '.*md'

# Find a file containing a pattern
grep -e '.*png' *

# Find a file by name and content
find . -regex '.*md' -exec grep -l -e 'Tensorflow' {} \;

# Copy from/to remote server with SHH
scp user@from-host:/path/to/source.file user@to-host:/path/to/destination.file

# Establish SOCKS 5 proxy to access a closed port (for example 18080)
ssh -D 18080 -f -C -q -N user@host

# Generate new SSH RSA key
ssh-keygen -t rsa -f /path/to/file
```

## Useful GIT commands
```shell
# Reset local repository to remote
git fetch --prune

# One line create branch with checkout command
git checkout -b branch_name

# Undo the most recent commit(s) and keep the changes
git reset --soft HEAD^

# Undo the most recent commit(s) and wipe the changes
git reset --hard HEAD^
```

### Using custom SSH key
1. Open ~/.ssh/config
2. Add a record:
```shell
Host github-my-repo
    # The host that has the remote Git repository
    Hostname github.com
    # Username for remote SSH user (For GitHub, everyone uses the name `git`)
    User git
    # Path to your private SSH key
    IdentityFile /root/.ssh/github-my-repo.id.rsa
```

### Customizing Github repo
1. Open .git/config file in a local repo
2. Add the following lines:
```
[user]
        name = %User Name%
        email = %user_email%
[core]
        sshCommand = "ssh -i ~/.ssh/id_rsa"
```

## Useful Docker commands
```shell
# Getting all containers:
docker ps -a

# Getting all images
docker images

# Stopping all containers
docker stop $(docker ps -a -q)

# Removing all stopped containers
# Where -a shows all containers, -q defines a representation with IDs only and -f defines filter status=exited
docker rm $(docker ps -a -q -f status=exited)

# Getting into Docker containers
docker exec -it <mycontainer> bash

# Remove an image from local Docker repository
docker rmi <image>
```