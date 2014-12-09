
set terminal postscript

set grid ytics lt 0 lw 1 lc rgb "#bbbbbb"
set grid xtics lt 0 lw 1 lc rgb "#bbbbbb"

set output "controldeps.eps"
set title "Control Dependencies"
set xlabel "Input size (bytes)"
set ylabel "Seconds (s)"
plot "with-alg-0-10000-100-controlDeps.csv" every ::1 using 1:2 \
     title "Object Algebra", \
     "with-ast-0-10000-100-controlDeps.csv" every ::1 using 1:2 \
     title "AST" pt 6


set output "typeenv.eps"
set title "Extract Type Environment"
set xlabel "Input size (bytes)"
set ylabel "Seconds (s)"
plot "with-alg-0-10000-100-typeEnv.csv" every ::1 using 1:2 \
     title "Object Algebra", \
     "with-ast-0-10000-100-typeEnv.csv" every ::1 using 1:2 \
     title "AST" pt 6

set output "renamevar.eps"
set title "Rename Variable"
set xlabel "Input size (bytes)"
set ylabel "Seconds (s)"
plot "with-alg-0-10000-100-rename.csv" every ::1 using 1:2 \
     title "Object Algebra", \
     "with-ast-0-10000-100-rename.csv" every ::1 using 1:2 \
     title "AST" pt 6
