FROM ubuntu:20.04

WORKDIR app

RUN apt update && apt install -y texlive-base texlive-latex-base texlive-plain-generic texlive-fonts-recommended  texlive-latex-recommended texlive-latex-extra texlive-fonts-extra texlive-bibtex-extra texlive-pictures texlive-science texlive-lang-portuguese texlive-lang-english lmodern biber latexmk 

COPY . .

CMD make
