

all: plots/controldeps.pdf plots/rename.pdf plots/inline.pdf plots/typeenv.pdf 


plots/controldeps.eps: ../QL/resources/benchmark/genplot.plg
	(cd ../QL/resources/benchmark/; \
	 gnuplot -e "astData='with-ast-0-1000-10-controlDeps.csv'; algData='with-alg-0-1000-10-controlDeps.csv'; theTitle='Control dependencies'" genplot.plg > ../../../paper/$@; \
	cd -)

plots/rename.eps: ../QL/resources/benchmark/genplot.plg
	(cd ../QL/resources/benchmark/; \
	 gnuplot -e "astData='with-ast-0-1000-10-rename.csv'; algData='with-alg-0-1000-10-rename.csv'; theTitle='Rename variable'" genplot.plg > ../../../paper/$@; \
	cd -)

plots/inline.eps: ../QL/resources/benchmark/genplot.plg
	(cd ../QL/resources/benchmark/; \
	 gnuplot -e "astData='with-ast-0-1000-10-inline.csv'; algData='with-alg-0-1000-10-inline.csv'; theTitle='Inline conditions'" genplot.plg > ../../../paper/$@; \
	cd -)

plots/typeenv.eps: ../QL/resources/benchmark/genplot.plg
	(cd ../QL/resources/benchmark/; \
	 gnuplot -e "astData='with-ast-0-1000-10-typeEnv.csv'; algData='with-alg-0-1000-10-typeEnv.csv'; theTitle='Type environment'" genplot.plg > ../../../paper/$@; \
	cd -)



%.pdf: %.eps
	ps2pdf $< plots/`basename $@`


.PHONY: clean

clean:
	rm -f plots/*.{eps,pdf}
