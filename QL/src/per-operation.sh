
echo "*******************FreeVars"
cloc.pl --quiet _syb/query/FreeVars.java

echo "*******************Type Env"
cloc.pl --quiet _syb/query/TypeEnv.java

echo "*******************Controldeps"
cloc.pl --quiet _syb/query/ControlDepGraphUnless.java  _syb/query/ControlDepGraph.java

echo "*******************Data deps"
cloc.pl --quiet _syb/query/DataDepGraph.java

echo "*******************Desugar repeat"
cloc.pl --quiet _syb/trafo/DesugarRepeat.java

echo "*******************Desugar unless"
cloc.pl --quiet _syb/trafo/DesugarUnless.java

echo "*******************Rename"
cloc.pl --quiet _syb/trafo/RenameVariable.java

echo "*******************Inline conditions"
cloc.pl --quiet _syb/trafo/InlineConditions.java _syb/trafo/InlineConditionsUnless.java

echo "*******************Alg interfaces"
cloc.pl --quiet ql_obj_alg/syntax/IExpAlg.java ql_obj_alg/syntax/IFormAlg.java ql_obj_alg/syntax/IStmtAlg.java

