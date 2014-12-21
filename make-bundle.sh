
rm -rf tempdir
mkdir -p tempdir/Shy

cp -r Library tempdir/Shy
cp -r ObjectAlgebras tempdir/Shy
cp -r naked-object-algebras tempdir/Shy
cp -r QL tempdir/Shy

cd tempdir
rm -r Shy/Library/bin/*
rm -r Shy/ObjectAlgebras/bin/*
rm -r Shy/naked-object-algebras/bin/*
rm -r Shy/QL/bin/*

zip -r Shy.zip Shy

cd ..
mv tempdir/Shy.zip .
