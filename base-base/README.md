# Base base configuration

This is meant to the most minimal configuration and others will build off of it.

## `.editorconfig`

Editorconfig files are an excelent way of providing shared configurations for multiple editors
across a project.

This line means tools will not bubble higher up the directory tree to get other configs. This
ensures some personal editorconfig file you may have does not "contaminate" your project's settings.
An assumption here is that the project will be a team project so that is a desirable goal.

    root = true

These apply to all files (but can be overridden). `utf-8` is a good character set that all files
should use. Trailing whitespace being absent helps keep things cleaner. Final newlines help some
tools which assume it will be there (if anything merely having the warning gone from the git diff
is nice).

Notably absent is an `end_of_line = lf`. This causes more trouble than it is worth on Windows. It is
better to allow files to be created with the native line endings and let git handle conversions.

    [*]
    charset = utf-8
    trim_trailing_whitespace = true
    insert_final_newline = true

Markdown uses two trailing spaces to indicate a newline but without starting a new paragraph.
Because of this you cannot assume trailing whitespace is a mistake.

    [*.md]
    trim_trailing_whitespace = false

I cannot find my original source for this but I was under the impression Windows scripts behave
incorrectly if the files are in `utf-8` so here we declare `latin1`. Technically this is not the
Windows default but is something super close to it so it should be fine. Also we explicitly ensure
Windows scripts use `crlf` endings.

    [*.{bat, cmd, ps1}]
    charset = latin1
    end_of_line = crlf

Shell scripts should end in `lf` always. The only time this isn't necesscary is in Cygwin which
normally has an option to make `cr` not cause problems in scripts. However the abscence of it does
not cause problems in Cygwin. Truthfully only `.sh` is needed but `.bash` may be used. You may want
to add stuff like `.zsh` if you use those scripts.

    [*.{sh, bash}]
    end_of_line = lf

## `.gitattributes`

gitattributes can configure the way git works with certain files (by pattern). It is useful because
in large team settings you cannot always rely on everyone properly setting thier git config.

By default for all files we use `text=auto`. You can read more in git's documentation but the gist
is that this automatically detects if the file is binary or text and if it is text git will handle
the EOL conversion (basically meaning converting `CRLF` to `LF` on commit for everyone and `LF` to
`CRLF` on checkout for Windows users).

    * text=auto

**It is important for gitattributes and editorconfig line endings to match.** If they don't you will
get a lot of noisy warnings and diffs. `text` is the same as `text=auto` but it forces git to assume
it is text instead of binary.

    *.bat text eol=crlf
    *.cmd text eol=crlf
    *.ps1 text eol=crlf

    *.sh text eol=lf
    *.bash text eol=lf

## `.gitignore`

This is empty because this is the base-most base. It is present because it is such an important file
in a repository. I have no good argument for its inclusion even when empty other than reading and
believeing that the initial commit of a repository should include an empty `.gitignore` file.
