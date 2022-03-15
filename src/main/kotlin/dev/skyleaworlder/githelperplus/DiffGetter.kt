package dev.skyleaworlder.githelperplus

import org.eclipse.jgit.diff.DiffFormatter
import org.eclipse.jgit.lib.ObjectId
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.revwalk.RevCommit
import java.io.ByteArrayOutputStream

class DiffGetter(private val repository: Repository) {
    fun getDiff(a: String, b: String): String {
        return this.getDiff(
            repository.resolve(a),
            repository.resolve(b)
        )
    }

    fun getDiff(a: ObjectId, b: ObjectId): String {
        return this.getDiff(
            repository.parseCommit(a),
            repository.parseCommit(b)
        )
    }

    fun getDiff(a: RevCommit, b: RevCommit): String {
        val out = ByteArrayOutputStream()
        val diffFmt = DiffFormatter(out)
        diffFmt.setRepository(repository)
        diffFmt.format(a, b)
        return out.toString()
    }
}